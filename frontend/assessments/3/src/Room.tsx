import { useState } from "react";
import { RoomType, AddOn } from "./interface";
import { Calendar } from "./Calender";
import { createUseStyles } from "react-jss";

const useStyles = createUseStyles({
  body: {
    color: "#000",
    width: "100%",
    height: "100%",
    backgroundColor: "#fff",
    marginLeft: "5rem",
    display: "flex",
    flexDirection: "column",
  },
  header: { 
    marginBottom: "1rem",
    marginLeft: "15rem",
  },
  ItemHeader: {
    display:"flex",
    width: "170%",
    height: "3rem",
    marginTop: "4rem",
    backgroundColor: "#f08a5d",
    color: "#fff",
    fontSize: "1.2rem",
    alignItems: "center",
  },
  items: {
    listStyleItem: "none",
    backgroundColor: "#fff",
    border:"2px solid black", 
    padding: "0.5rem", 
    marginBottom: "0.5rem",
    marginLeft:"1rem",
    marginTop:"0.5rem"
  },
  itemList: {
    display: "flex",
  },
  submitButton: {
    marginTop: "4rem",
    marginLeft: "5rem",
    backgroundColor: "#f08a5d",
    color: "#fff",
    "&:disabled": {
      opacity: "0.5",
    },
  },
  calendar: {
    backgroundColor: "#fff",
    padding: "1rem",
    border: "2px solid #000", 
    borderRadius: "5px", 
    marginTop: "1rem", 
  },
  cost: {
    marginTop: '1rem', 
    marginLeft: '5rem', 
    fontSize: '1.5rem', 
  },
});

interface IRoomProps {
  rooms: RoomType[];
}

export function Room({ rooms }: Readonly<IRoomProps>) {
  const classes = useStyles();
  const [selectedRoom, setSelectedRoom] = useState<RoomType | null>(null);
  const [selectedAddOns, setSelectedAddOns] = useState<AddOn[]>([]);
  const [selectedStartDate, setSelectedStartDate] = useState<Date | null>(
    null
  );
  const [selectedEndDate, setSelectedEndDate] = useState<Date | null>(null);
  const [totalCost, setTotalCost] = useState<number>(0);

  const calculateCost = () => {
    if (!selectedRoom || !selectedStartDate || !selectedEndDate) return 0;

    const oneDay = 24 * 60 * 60 * 1000;
    const startDate = selectedStartDate.getTime();
    const endDate = selectedEndDate.getTime();
    const numberOfDays =
      Math.round(Math.abs((endDate - startDate) / oneDay)) + 1;

    const roomCost = selectedRoom.costPerNight * numberOfDays;

    const addOnsCost = selectedAddOns.reduce(
      (total, addOn) => total + addOn.cost * numberOfDays,
      0
    );

    const subtotal = roomCost + addOnsCost;
    const tax = subtotal * 0.18;
    const totalCost = subtotal + tax;

    return totalCost;
  };

  const handleSubmit = () => {
    if (
      !isValidDate(selectedStartDate) ||
      !isValidDate(selectedEndDate)
    ) {
      alert("Please select valid start and end dates.");
      return;
    }

    const cost = calculateCost();
    setTotalCost(cost);

    console.log("Selected Room: ", selectedRoom);
    console.log("Selected AddOns: ", selectedAddOns);
    console.log("Selected Start Date: ", selectedStartDate);
    console.log("Selected End Date: ", selectedEndDate);
    console.log("Total Cost: ", cost);
  };

  const isValidDate = (date: Date | null) => {
    return date instanceof Date && !isNaN(date.getTime());
  };

  const isSubmitEnabled =
    selectedRoom && selectedStartDate && selectedEndDate && selectedAddOns;

  return (
    <div className={classes.body}>
      <h1 className={classes.header}>Hotel Booking</h1>
      <div>
        <p className={classes.ItemHeader}>Select Room Type</p>
        <ul className={classes.itemList}>
          {rooms.map((room) => (
            <li
              className={classes.items} 
              key={room.id}
              onClick={() => setSelectedRoom(room)}
            >
              {room.name}
            </li>
          ))}
        </ul>
      </div>
      <div>
        <p className={classes.ItemHeader}>Select Date</p>
        <div className={classes.calendar}> 
          <Calendar
            startDate={selectedStartDate || new Date()}
            endDate={selectedEndDate || new Date()}
            onStartDateChange={(date) => setSelectedStartDate(date)}
            onEndDateChange={(date) => setSelectedEndDate(date)}
          />
        </div>
      </div>
      <div>
        <p className={classes.ItemHeader}>
          Select Additional Add ons/preferences
        </p>
        {selectedRoom && (
          <div>
            <ul className={classes.itemList}>
              {selectedRoom.addOns.map((addOn: AddOn, index: number) => (
                <li
                  className={classes.items}
                  key={index}
                  onClick={() =>
                    setSelectedAddOns((prevAddOns) => [...prevAddOns, addOn])
                  }
                >
                  {addOn.name}
                </li>
              ))}
            </ul>
          </div>
        )}
      </div>

      <button
        className={classes.submitButton}
        onClick={handleSubmit}
        disabled={!isSubmitEnabled}
      >
        Submit
      </button>
      {totalCost > 0 && (
        <h2 className={classes.cost}>Cost + 18%GST : {totalCost.toFixed(2)} INR</h2>
      )}    
    </div>
  );
}
