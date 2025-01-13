import React from 'react'
import AddressCart from '../AddressCart/AddressCart'
import { Button } from '@mui/material'
import CartItem from '../Cart/CartItem'

const OrderSummary = () => {
  return (
    <div>
        <div className="p-5 shadow-lg rounded-s-md border">
            <AddressCart />
            
        </div>
        <div>
      <div className="lg:grid grid-cols-3 relative mt-6">
        <div className="col-span-2">
          {[1, 1, 1].map((item, index) => (
            <CartItem key={index} />
          ))}
        </div>

        <div className="px-5 sticky top-0 h-[100vh] mt-5 lg:mt-0 ">
          <div className="border p-5">
            <p className="uppercase font-bold opacity-60 pb-4 ">
              Price details
            </p>
            <hr />
            <div className="space-y-3 font-semibold mb-10">
              <div className="flex justify-between pt-3 text-black">
                <span>Price</span>
                <span>₹4667</span>
              </div>
              <div className="flex justify-between pt-3 text-black">
                <span>Discount</span>
                <span className="text-green-600">-₹3419</span>
              </div>
              <div className="flex justify-between pt-3 text-black">
                <span>Delivery Chargn</span>
                <span className="text-green-600">free</span>
              </div>
              <div className="flex justify-between pt-3 font-bold">
                <span>Total Amount</span>
                <span className="text-green-600">₹1278</span>
              </div>
            </div>
            <Button
              variant="contained"
              className="w-full mt-5"
              sx={{
                px: "2.5rem",
                py: ".7rem",
                bgcolor: "#9155fd",
                marginTop: "1rem",
              }}
            >
              Checkout
            </Button>
          </div>
        </div>
      </div>
    </div>
    </div>
  )
}

export default OrderSummary