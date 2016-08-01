//tag::annotations[]
import {Component, OnInit} from "@angular/core";

@Component({
	templateUrl: "app/components/stars/stars.html",
	selector: "auction-stars",
	inputs: ["rating", "count"]
})
// end::annotations[]
// tag::class[]
export default class StarsComponent implements OnInit {
	count: number = 5;
	rating: number = 0;
	stars: boolean[] = [];

	ngOnInit() {
		for (let i = 1; i <= this.count; i++) {
			this.stars.push(i > this.rating);
		}
	}
}