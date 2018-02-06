/**
 * breadcrumb data model
 */
export interface Breadcrumb {
    isRoute: boolean;       // does this have a related route link
    routerLink: string;     // route link url
    name: string;           // breadcrumb display name
    from?: string;          // parent page
}
