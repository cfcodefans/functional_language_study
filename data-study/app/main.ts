import {bootstrap} from "@angular/platform-browser-dynamic";
import {Component} from "@angular/core";
import {HTTP_PROVIDERS, JSONP_PROVIDERS } from '@angular/http';

import {SH_STOCK_IDS} from "./services/stock-ids";
import {Stock} from "./services/stocks";

import SideNavComponent from "./components/sidenav/sidenav";
import StockBoardComponent from "./components/stocks/stockboard";
import MarketIndexComponent from "./components/stocks/market-index";


@Component({
	selector: "app",
	templateUrl: "app/app.html",
	directives:[
	SideNavComponent,
	StockBoardComponent,
	MarketIndexComponent,]
})
class AppComponent {
}

bootstrap(AppComponent, [HTTP_PROVIDERS, JSONP_PROVIDERS]);