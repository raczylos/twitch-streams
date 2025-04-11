import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
    name: 'thumbnail',
})
export class ThumbnailPipe implements PipeTransform {
    transform(
        value: string,
        width: number = 300,
        height: number = 180,
    ): string {
        return value
            .replace('{width}', width.toString())
            .replace('{height}', height.toString());
    }
}
