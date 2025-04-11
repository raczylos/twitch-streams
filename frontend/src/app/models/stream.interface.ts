import { Game } from './game.interface';

export interface Stream {
    id: number;
    title: string;
    viewerCount: number;
    twitchStreamId: string;
    thumbnailUrl: string;
    language: string;
    startedAt: string;
    streamer: {
        id: number;
        twitchUserId: string;
        login: string;
        displayName: string;
        profileImageUrl: string;
    };
    game: Game;
}
