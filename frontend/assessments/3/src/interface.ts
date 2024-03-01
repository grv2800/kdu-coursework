export interface InterfaceTs {
    roomTypes: RoomType[];
}

export interface RoomType {
    id:           number;
    name:         string;
    costPerNight: number;
    currency:     Currency;
    addOns:       AddOn[];
}

export interface AddOn {
    name:     string;
    cost:     number;
    currency: Currency;
}

export enum Currency {
    Inr = "INR",
}
