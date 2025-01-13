import { Step, StepLabel, Stepper } from '@mui/material'
import React from 'react'

const steps = [
    'Placed',     
    'Order Confirmation',
    'Shipped', 
    'Out of Delivery',
    'Delivered',   
]
const OrderTraker = ({activeStep}) => {
  return (
    <div className='w-full'>
      <Stepper activeStep={activeStep} alternativeLabel>
        {steps.map((label)=>
          <Step><StepLabel sx={{color:"#9155FD",fontSize:"44px"}}>{label}</StepLabel></Step>)}
      </Stepper>
    </div>
  )
}

export default OrderTraker