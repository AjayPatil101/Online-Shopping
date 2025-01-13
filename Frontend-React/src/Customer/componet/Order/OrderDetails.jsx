import React from "react";
import AddressCart from "../AddressCart/AddressCart";
import OrderTraker from "./OrderTraker";
import { Box, Grid } from "@mui/material";
import { deepPurple } from "@mui/material/colors";
import StarBorderIcon from "@mui/icons-material/StarBorder";
import { mens_kurta } from "../../../Data/mens_kurta";

const OrderDetails = () => {
  return (
    <div className="px:5 lg:px-20">
      <div>
        <h1 className="font-bold text-xl py-7">Delivery Address</h1>

        <AddressCart />
      </div>
      {/* Other Order details */}
      <div className="py-20">
        <OrderTraker activeStep={3} />
      </div>
      <Grid container className="space-x-5">
        {[1, 1, 1, 1, 1, 1].map((items) => (
          <Grid
            item
            container
            className="shadow-xl rounded-md p-5 border"
            sx={{ alignItems: "center", justifyContent: "space-between" }}
          >
            <Grid item xs={6}>
              <div className="flex items-center space-x-4">
                <img
                  className="w-[5rem] h-[5rem] object-cover object-top"
                  src="https://rukminim1.flixcart.com/image/612/612/kb1470w0/jean/x/r/a/30-11274626-roadster-original-imafsgsthk6gdpjg.jpeg?q=70"
                  alt=""
                />
                <div className="space-y-2 ml-5">
                  <p className="font-semibold">
                    Men Regular Mid Rise Blue Jeans
                  </p>
                  <p className="space-x-5 opacity-50 text-xs font-semibold ">
                    <span>Color: Black</span>
                    <span>Size: M</span>
                  </p>
                  <p
                  // className="opacity-50 text-xs"
                  >
                    Seller: Roadster
                  </p>
                  <p>₹1009</p>
                </div>
              </div>
            </Grid>
            <Grid item>
              <Box sx={{ color: deepPurple[500] }}>
                <StarBorderIcon sx={{ fontSize: "2rem" }} className="px-2" />
                <span>Rate & Review Product</span>
              </Box>
            </Grid>
          </Grid>
        ))}
      </Grid>
    </div>
  );
};

export default OrderDetails;
