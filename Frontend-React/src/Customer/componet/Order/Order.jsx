import { Grid } from "@mui/material";
import React from "react";
import OrderCart from "./OrderCart";

const orderStatus = [
  { label: "On The Way", value: "On The Way" },
  { label: "Delivered", value: "Delivered" },
  { label: "Cancelled", value: "Cancelled" },
  { label: "Return Requested", value: "Return Requested" },
  { label: "Returned", value: "Returned" },
  { label: "Payment Failed", value: "Payment Failed" },
];

const Order = () => {
  return (
    <div className="mx-5">
      <Grid container sx={{ justifyContent: "space-between" }}>
        <Grid item xs={2.5}>
          <div className="h-auto shadow-lg bg-white p-5 sticky top-5">
            <h1 className="font-bold text-lg">Filter</h1>
            <div className="space-y-4 mt-7">
              <h1 className="font-semibold">ORDER STATUS</h1>
              {orderStatus.map((option) => (
                <div className="flex items-center" key={option.value}>
                  <input
                    defaultValue={option.value}
                    type="checkbox"
                    className="h-4 w-4 border-gray-300 text-indigo-600 focus:ring-indigo-500"
                  />
                  <label
                    className="text-gray-600 ml-3 text-sm"
                    htmlFor={option.value}
                  >
                    {option.label}
                  </label>
                </div>
              ))}
            </div>
          </div>
        </Grid>
        <Grid item xs={9}>
          <div className="space-y-5">
            {[1, 1, 1, 1, 1, 1, 1, 1, 1].map((item, index) => (
              <OrderCart key={index} />
            ))}
          </div>
        </Grid>
      </Grid>
    </div>
  );
};

export default Order;
