import * as Subject from '@reactivex/rxjs/dist/cjs/Subject';
import {bootstrap} from 'angular2/platform/browser';
import {Component, Directive} from 'angular2/core';

@Directive({
	selector: "input[log-directive]",
	host: {
		"(input)": "onInput($event)"
	}
})
class LogDirective {
	onInput(event) {
		console.info(event.target.value);
	}
}

@Component({
	selector: "directive-example",
	directives: [LogDirective],
	template: "<input type='text' log-directive>"
})
class DirectiveExample {}

bootstrap(DirectiveExample);