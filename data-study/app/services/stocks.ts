import {Injectable} from "@angular/core";
import {Http, Jsonp, URLSearchParams, Response} from "@angular/http";

import {Observable} from "rxjs/Observable";

import './rxjs-imports';



export class Stock {
	constructor(public id:{c:string; t:string}) {
		
	}
}

//http://hq.sinajs.cn/?list=s_sh000001
//var hq_str_s_sh000001="上证指数,3089.7055,4.9004,0.16,1613689,17918349";
//指数名称，当前点数，上涨/下跌点数，涨跌率，成交量（手），成交额（万元）
const SH_MARKET_INDEX_URL = "/source/?list=s_sh000001";

export class MarketIndex {
	constructor(
		public name:string,
		public index: number,
		public update: number,
		public percent: number,
		public transaction: number,
		public amount: number
		) {}

	public static fromData(data: string = null): MarketIndex {
		if (Utils.isBlank(data)) return new MarketIndex("xxx", 0,0,0,0,0);

		let matches = data.match("\".*\"");
		let valStr = matches[0].slice(1, -1);
		let vals = valStr.split(",").map(v => v.trim());
		return new MarketIndex(vals[0], //name
			parseFloat(vals[1]), //index
			parseFloat(vals[2]), //update
			parseFloat(vals[3]), //percent
			parseFloat(vals[4]), //transaction
			parseFloat(vals[5])); //amount
	}

	public toString(): string {
		return JSON.stringify(this);
	}
}

@Injectable()
export class MarketIndexService {
	constructor(private http: Http, private jsonp: Jsonp) {}

	getShMarketIndex(): Observable<MarketIndex> {
		return this.http.get(SH_MARKET_INDEX_URL)
					.map((resp: Response) => {
						return MarketIndex.fromData(resp.text());
					});
					// .catch(errorHandle);
		// let params = new URLSearchParams();
		// params.set("callback", "Console.info");
		// params.set("list", "s_sh000001");
		// return this.jsonp.get(SH_MARKET_INDEX_URL, {search: params})
		// 					.map((resp: Response) => {
		// 						return MarketIndex.fromData(resp.text());
		// 					});
	}
}

export class Utils {
	public static isBlank(str: string): boolean {
		return !str || str.trim().length == 0;
	}


	public static errorHandle(error: any) {
		console.error(error);
		let errMsg = (error.message) ? error.message : 
			(error.status ? `${error.status} - ${error.statusText}` : "server-error");
		console.error(errMsg);
		return Observable.throw(errMsg);
	}
}