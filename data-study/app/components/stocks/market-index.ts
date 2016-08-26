import {Component, OnInit} from "@angular/core";
import {MarketIndex, MarketIndexService, Utils} from "../../services/stocks";

@Component({
	selector: "market-index",
	providers:[MarketIndexService],
	templateUrl: "app/components/stocks/market-index.html"
})
export default class MarketIndexComponent implements OnInit {
	
	sh: MarketIndex = MarketIndex.fromData();

	constructor(private marketIndexService:MarketIndexService) {
		
	}

	load_sh() {
		this.marketIndexService.getShMarketIndex().subscribe(mi => {
			this.sh = mi;
		}, Utils.errorHandle);
	}

	ngOnInit() {
		this.load_sh();
	} 
}