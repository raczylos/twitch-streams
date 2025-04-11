export interface PaginatedResponse<T> {
    content: T[];
    totalPages: number;
    totalElements: number;
    last: boolean;
    numberOfElements: number;
    size: number;
    number: number;
    sort: {
        unsorted: boolean;
        sorted: boolean;
        empty: boolean;
    };
    first: boolean;
    empty: boolean;
}
