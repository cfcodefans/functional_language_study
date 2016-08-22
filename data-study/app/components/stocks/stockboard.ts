import {Component} from "@angular/core";

import {SH_STOCK_IDS} from "../../services/stock-ids";
import {Stock} from "../../services/stocks";

@Component({
	selector: "stock-board",
	templateUrl: "app/components/stocks/stockboard.html"
})
export default class StockBoardComponent {
	shStocks: Array<Stock>;
	constructor() {
		this.shStocks = SH_STOCK_IDS.map(id => new Stock(id));
	}
}