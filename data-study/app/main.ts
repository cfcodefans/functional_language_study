import {bootstrap} from "@angular/platform-browser-dynamic";
import {Component} from "@angular/core";

import {SH_STOCK_IDS} from "./services/stock-ids";
import {Stock} from "./services/stocks";


@Component({
	selector: "app",
	templateUrl: "app/app.html" 
})
class AppComponent {
	shStocks: Array<Stock>;
	constructor() {
		this.shStocks = SH_STOCK_IDS.map(id => new Stock(id));
	}
}

bootstrap(AppComponent);