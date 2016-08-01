import {Component} from "@angular/core";
import {ActivatedRoute} from "@angular/router";
import {Product, ProductService} from "../../services/product-service";


@Component({
	selector: "auction-product-page",
	providers: [ProductService],
	templateUrl: "app/components/product-detail/product-detail.html"
})
export default class ProductDetailComponent {
	prodTitle:string;
	product: Product;
	constructor(route: ActivatedRoute, private prodService:ProductService) {
		console.info("ProductDetailComponent.constructor");
		this.prodTitle = route.snapshot.params["prodTitle"];
		this.product = this.prodService.searchByTitle(this.prodTitle);
	}
}