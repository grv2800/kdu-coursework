export interface Istocks {
    stock_name:   string;
    stock_symbol: string;
    base_price:   string;
}

export interface Itransactions {
    stock_name:        string;
    stock_symbol:      string;
    transaction_price: number;
    timestamp:         string;
    status:            string;
}

