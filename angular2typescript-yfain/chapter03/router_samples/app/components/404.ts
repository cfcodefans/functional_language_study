import { style } from '.2.4.3@@angular/core';
import { selector } from 'rxjs/operator/publish';
import { Component } from '@angular/core';

@Component({
    selector: "home",
    template: "<h1 class='home'>Dear friend, this URL was not found</h1>",
    styles: [".home {background: yellow}"]
})
export class _404Component {}