import React, { useState } from "react";
import { createUseStyles } from "react-jss";

const useStyles = createUseStyles({
  input: {
    backgroundColor: "#fff",
    border: "1px solid #000",
    borderRadius: "5px",
    padding: "0.5rem",
    margin: "1rem",
    color: "#000",
  },
});

interface CalendarProps {
  startDate: Date;
  endDate: Date;
  onStartDateChange: (date: Date) => void;
  onEndDateChange: (date: Date) => void;
}

export function Calendar({
  startDate,
  endDate,
  onStartDateChange,
  onEndDateChange,
}: CalendarProps) {
  const classes = useStyles();
  const formatDateString = (date: Date): string => {
    const year = date.getFullYear();
    const month = (date.getMonth() + 1).toString().padStart(2, "0");
    const day = date.getDate().toString().padStart(2, "0");
    return `${year}-${month}-${day}`;
  };

  const [startDateValue, setStartDateValue] = useState<Date>(
    startDate || new Date()
  );
  const [endDateValue, setEndDateValue] = useState<Date>(endDate || new Date());

  const handleStartDateChange = (
    event: React.ChangeEvent<HTMLInputElement>
  ) => {
    const selectedStartDate = new Date(event.target.value);
    setStartDateValue(selectedStartDate);
    onStartDateChange(selectedStartDate);
    if (endDateValue && selectedStartDate > endDateValue) {
      setEndDateValue(selectedStartDate);
      onEndDateChange(selectedStartDate);
    }
  };

  const handleEndDateChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    const selectedEndDate = new Date(event.target.value);
    setEndDateValue(selectedEndDate);
    onEndDateChange(selectedEndDate);
    if (startDateValue && selectedEndDate < startDateValue) {
      setStartDateValue(selectedEndDate);
      onStartDateChange(selectedEndDate);
    }
  };

  return (
    <div>
      <label htmlFor="start-date">Start Date:</label>
      <input
        className={classes.input}
        type="date"
        id="start-date"
        value={formatDateString(startDateValue)}
        onChange={handleStartDateChange}
      />

      <label htmlFor="end-date">End Date:</label>
      <input
        className={classes.input}
        type="date"
        id="end-date"
        value={formatDateString(endDateValue)}
        onChange={handleEndDateChange}
        min={formatDateString(startDateValue)}
      />
    </div>
  );
}
