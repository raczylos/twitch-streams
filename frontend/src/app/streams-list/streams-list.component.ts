import { Component, OnInit, ViewChild } from '@angular/core';
import { StreamService } from '../services/stream.service';
import { GameService } from '../services/game.service';
import { Stream } from '../models/stream.interface';
import { Game } from '../models/game.interface';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { FormControl } from '@angular/forms';
import {
    debounceTime,
    distinctUntilChanged,
    switchMap,
    catchError,
} from 'rxjs/operators';
import { of } from 'rxjs';

@Component({
    selector: 'app-streams-list',
    templateUrl: './streams-list.component.html',
    styleUrls: ['./streams-list.component.css'],
})
export class StreamsListComponent implements OnInit {
    streams: Stream[] = [];
    isLoading = true;
    isFetched = false;
    totalElements = 0;
    pageSize = 10;
    pageIndex = 0;
    pageSizeOptions = [5, 10, 20, 50];

    gameFilterControl = new FormControl('');
    currentGame: Game | null = null;

    @ViewChild(MatPaginator) paginator!: MatPaginator;

    constructor(
        private streamService: StreamService,
        private gameService: GameService,
    ) {}

    ngOnInit(): void {
        this.setupGameFilter();
        this.loadStreams(this.pageIndex, this.pageSize);
    }

    private setupGameFilter(): void {
        this.gameFilterControl.valueChanges
            .pipe(
                debounceTime(300),
                distinctUntilChanged(),
                switchMap((gameName) => {
                    if (!gameName) {
                        this.currentGame = null;
                        this.pageIndex = 0;
                        return of(null);
                    }
                    return this.gameService.getGameByName(gameName).pipe(
                        catchError(() => {
                            this.currentGame = null;
                            return of(null);
                        }),
                    );
                }),
            )
            .subscribe((game) => {
                this.currentGame = game;
                this.pageIndex = 0;
                this.loadStreams(this.pageIndex, this.pageSize);
            });
    }

    addStreamsFromTwitch(): void {
        this.isFetched = true;
        this.streamService.addStreamsFromTwitch().subscribe({
            next: () => {
                this.loadStreams(this.pageIndex, this.pageSize);
            },
            error: (err) => console.error('Error:', err),
        });
    }

    loadStreams(pageIndex: number, pageSize: number): void {
        this.isLoading = true;

        const streamObservable = this.currentGame
            ? this.streamService.getStreamsByGame(
                  this.currentGame.twitchGameId,
                  pageIndex,
                  pageSize,
              )
            : this.streamService.getStreams(pageIndex, pageSize);

        streamObservable.subscribe({
            next: (data) => {
                if (data.content.length != 0) {
                    this.streams = data.content;
                    this.totalElements = data.totalElements;
                    this.isLoading = false;
                }
            },
        });
    }

    onPageChange(event: PageEvent): void {
        this.pageSize = event.pageSize;
        this.pageIndex = event.pageIndex;
        this.loadStreams(this.pageIndex, this.pageSize);
    }

    getStreamDuration(startedAt: string): string {
        const now = new Date();
        const diff = Math.floor(
            (now.getTime() - new Date(startedAt).getTime()) / 1000,
        );

        const hours = Math.floor(diff / 3600);
        const minutes = Math.floor((diff % 3600) / 60);

        if (hours > 0) {
            return `${hours}h ${minutes}m`;
        }
        return `${minutes}m`;
    }

    clearFilter(): void {
        this.gameFilterControl.setValue('');
        this.currentGame = null;
    }
}
