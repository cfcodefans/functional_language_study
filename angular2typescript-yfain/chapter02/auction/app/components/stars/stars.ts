import {Component, Input, OnInit} from "@angular/core";

@Component({
	templateUrl: "app/components/stars/stars.html",
	styles: [`.starrating {color: #d17581; }`],
	selector: "auction-stars"
})
export default class StarsComponent extends OnInit {
	@Input() count: number = 5; // <1>
	@Input() rating: number = 0;
	stars: boolean[] = [];	
	
	ngOnInit() {
		for (let i = 1; i <= this.count; i++) {
			this.stars.push(i > this.rating);
		}
	}
}