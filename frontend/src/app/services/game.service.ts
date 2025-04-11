import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Game } from '../models/game.interface';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root',
})
export class GameService {
    private apiUrl = 'http://localhost:8080/api/games';

    constructor(private http: HttpClient) {}

    getGameByName(name: string): Observable<Game> {
        return this.http.get<Game>(`${this.apiUrl}?name=${name}`);
    }
}
