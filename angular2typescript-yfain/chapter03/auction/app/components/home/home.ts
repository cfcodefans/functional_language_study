import {Component} from "@angular/core";
import {Product, ProductService} from "../../services/product-service";
import CarouselComponent from "../carousel/carousel";
import ProductItemComponent from "../product-item/product-item";

@Component({
	selector: "auction-home-page",
	providers: [ProductService],
	directives: [CarouselComponent, ProductItemComponent],
	styleUrls: ["/home.css"],
	templateUrl: "app/components/home/home.html",
})
export default class HomeComponent {
	products: Product[] = [];
	constructor(private productService: ProductService) {
		console.info("HomeComponent.constructor");
		this.products = this.productService.getProducts();
	}
}
