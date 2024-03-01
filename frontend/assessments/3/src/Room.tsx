import { useState } from "react";
import { RoomType, AddOn } from "./interface";
import { Calendar } from "./Calender";
import { createUseStyles } from 'react-jss';

const useStyles = createUseStyles({
    header:{
        marginTop:"5rem"
    },
    orangeBackground: {
    backgroundColor: 'orange',
  },
});

interface IRoomProps {
  rooms: RoomType[];
}

export function Room({ rooms }: Readonly<IRoomProps>) {
  const classes = useStyles();
  const [selectedRoom, setSelectedRoom] = useState<RoomType | null>(null);
  const [selectedAddOns, setSelectedAddOns] = useState<AddOn[]>([]);
  const [selectedStartDate, setSelectedStartDate] = useState<Date | null>(null);
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
    if (!isValidDate(selectedStartDate) || !isValidDate(selectedEndDate)) {
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

  const isSubmitEnabled = selectedRoom && selectedStartDate && selectedEndDate && selectedAddOns;

  return (
    <div>
      <h1 className={classes.header}>Hotel Booking</h1>
      <div>
        <p className={classes.orangeBackground}>Select Room Type</p>
        <ul>
          {rooms.map((room) => (
            <li key={room.id} onClick={() => setSelectedRoom(room)}>
              {room.name}
            </li>
          ))}
        </ul>
      </div>
      <div>
        <p className={classes.orangeBackground}>Select Date</p>
        <Calendar
          startDate={selectedStartDate || new Date()}
          endDate={selectedEndDate || new Date()}
          onStartDateChange={(date) => setSelectedStartDate(date)}
          onEndDateChange={(date) => setSelectedEndDate(date)}
        />
      </div>
      <div>
        <p className={classes.orangeBackground}>Select Additional Add ons/preferences</p>
        {selectedRoom && (
          <div>
            <ul>
              {selectedRoom.addOns.map((addOn: AddOn, index: number) => (
                <li
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

      <button onClick={handleSubmit} disabled={!isSubmitEnabled}>
        Submit
      </button>
      {totalCost > 0 && <div>Cost + 18%GST : ${totalCost.toFixed(2)}</div>}
    </div>
  );
}
