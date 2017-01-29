export enum HttpMethod {
    GET, POST, PUT, DELETE, HEAD, OPTIONS
}

export enum Source {
    /**
     * Cookie parameter injection source.
     */
    COOKIE,
    /**
     * Form parameter injection source.
     */
    FORM,
    /**
     * Header parameter injection source.
     */
    HEADER,
    /**
     * Uri parameter injection source.
     */
    URI,
    /**
     * Matrix parameter injection source.
     */
    MATRIX,
    /**
     * Path parameter injection source.
     */
    PATH,
    /**
     * Query parameter injection source.
     */
    QUERY,
    /**
     * Suspended async response injection source.
     */
    SUSPENDED,
    /**
     * Bean param parameter injection source.
     */
    BEAN_PARAM,
    /**
     * Unknown parameter injection source.
     */
    UNKNOWN
}

export interface IParam {
    value: any;
    name: string;
    source: Source;
}

export class Param implements IParam {
    value: any;
    name: string;
    source: Source;
    constructor(_value: any, _name: string, _source: Source) {
        this.value = _value;
        this.name = _name;
        this.source = _source;
    }
}

export interface IRestInvocation<R> {
    params: IParam[];
    method: HttpMethod;
    resultType: any;
    onSuccess(resp: any): R;
    onError(err: any);
    path: string;
    name: string;
    produceMediaTypes: string[];
    consumedMediaTypes: string[];
}


class ScalaRes {
    url: string = '/scala/scala';
    params(): IParam[] { return []; }
    helloworld(): IRestInvocation<number> {
        let _params: IParam[] = [];
        return <IRestInvocation<number>> {
            params: _params,
            method: HttpMethod.GET,
            resultType: 'null',
            path: '/scala/helloworld',
            name: 'helloworld',
            produceMediaTypes: ['text/plain'],
            consumedMediaTypes: []
        };
    }
}