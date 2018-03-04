"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
Object.defineProperty(exports, "__esModule", { value: true });
const console = require("console");
const mocha_typescript_1 = require("mocha-typescript");
let EnumStudy = class EnumStudy {
    tryEnum() {
        let Orientation;
        (function (Orientation) {
            Orientation[Orientation["EAST"] = 0] = "EAST";
            Orientation[Orientation["NORTH"] = 1] = "NORTH";
            Orientation[Orientation["WEST"] = 2] = "WEST";
            Orientation[Orientation["SOUTH"] = 3] = "SOUTH";
        })(Orientation || (Orientation = {}));
        console.info(Orientation);
        console.info(Orientation[0]);
        let Month;
        (function (Month) {
            Month[Month["Janurary"] = 0] = "Janurary";
            Month[Month["February"] = 1] = "February";
            Month[Month["March"] = 2] = "March";
            Month[Month["Apail"] = 3] = "Apail";
            Month[Month["May"] = 4] = "May";
            Month[Month["June"] = 5] = "June";
            Month[Month["July"] = 6] = "July";
            Month[Month["August"] = 7] = "August";
            Month[Month["September"] = 8] = "September";
            Month[Month["October"] = 9] = "October";
            Month[Month["November"] = 10] = "November";
            Month[Month["December"] = 11] = "December";
        })(Month || (Month = {}));
    }
};
__decorate([
    mocha_typescript_1.test
], EnumStudy.prototype, "tryEnum", null);
EnumStudy = __decorate([
    mocha_typescript_1.suite
], EnumStudy);
