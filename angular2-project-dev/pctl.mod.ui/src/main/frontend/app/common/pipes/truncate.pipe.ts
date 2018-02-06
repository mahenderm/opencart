import { Pipe, PipeTransform } from '@angular/core';

/**
 * Truncate a string to the given length and append suffix.
 * @param	length Text max length. Default 50.
 * @param	suffix Appended to the end of the string if truncted. Default ''.
 * @example Usage:
 * ```html
 * <p>{{ 'Hello world' | truncate:5:'...' }}</p>
 * <!-- Formats to: 'Hello...' -->
 * ```
 */

@Pipe ({ name: 'truncate' })
export class TruncatePipe implements PipeTransform {

    transform(value: string, args: string[]): any {
        let length = parseInt(args[0] || '50', 10), suffix = args[1] || '';
        if (value.length <= length) {
            return value;
        }
        return value.substring(0, length) + suffix;
    }
}
