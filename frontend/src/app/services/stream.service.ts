import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Stream } from '../models/stream.interface';
import { PaginatedResponse } from '../models/paginated-response.interface';

@Injectable({
    providedIn: 'root',
})
export class StreamService {
    private apiUrl = 'http://localhost:8080/api/streams';

    constructor(private http: HttpClient) {}

    getStreams(
        page: number = 0,
        size: number = 10,
    ): Observable<PaginatedResponse<Stream>> {
        return this.http.get<PaginatedResponse<Stream>>(
            `${this.apiUrl}?page=${page}&size=${size}`,
        );
    }

    addStreamsFromTwitch(): Observable<void> {
        return this.http.post<void>(`${this.apiUrl}`, null);
    }

    getStreamsByGame(
        gameId: string,
        page: number = 0,
        size: number = 10,
    ): Observable<PaginatedResponse<Stream>> {
        return this.http.get<PaginatedResponse<Stream>>(
            `${this.apiUrl}/game/${gameId}?page=${page}&size=${size}`,
        );
    }
}
