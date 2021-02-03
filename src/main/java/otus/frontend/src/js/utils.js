export const BACKEND_PORT = 8080;
export const MESSAGE_URL = '/message';
export const HEADERS_URL = '/table/headers';

export function getHost() {
    window.location.origin

    if (window.location.port === '3000') { // DEV mode
        return location.protocol + "//" + location.hostname + ":" + BACKEND_PORT;
    } else {
        return window.location.origin;
    }
}