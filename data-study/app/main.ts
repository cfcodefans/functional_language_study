import {bootstrap} from "@angular/platform-browser-dynamic";
import {Component} from "@angular/core";

import {SH_STOCK_IDS} from "./services/stock-ids";
import {Stock} from "./services/stocks";

import SideNavComponent from "./components/sidenav/sidenav";
import StockBoardComponent from "./components/stocks/stockboard";



@Component({
	selector: "app",
	templateUrl: "app/app.html",
	directives:[
		SideNavComponent,
		StockBoardComponent,
	]
})
class AppComponent {
}

bootstrap(AppComponent);