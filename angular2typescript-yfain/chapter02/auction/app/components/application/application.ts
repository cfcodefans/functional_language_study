// tag::Imports[]
import {Component, ViewEncapsulation} from "@angular/core";
import CarouselComponent from "../carousel/carousel";
import FooterComponent from "../footer/footer";
import NavbarComponent from "../navbar/navbar";
import ProductItemComponent from "../product-item/product-item";
import SearchComponent from "../search/search";
import {Product, ProductService} from "../../services/product-service";
// end::imports[]

// tag:annoation-component[]
@Component({
	selector: "auction-application", //<1>
	providers: [
		ProductService// <2>
		],
	templateUrl: "app/components/application/application.html",
	styleUrls: ["app/components/application/application.css"],
	directives: [
	CarouselComponent,
	FooterComponent,
	NavbarComponent,
	ProductItemComponent,
	SearchComponent],
	encapsulation: ViewEncapsulation.None
	})
// end:annoation-component[]
// tag::class[]
export default class ApplicationComponent {
	products: Array<Product> = []; //<1>
	constructor(private productService: ProductService) { //<2>
		this.products = this.productService.getProducts(); //<3>
	}
}