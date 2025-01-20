import { Button } from "@mui/material";
import { Grid, TextField } from "@mui/material";
import React, { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import { getUser, register } from "../../State/Auth/Action";

const RegisterForm = () => {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const jwt = localStorage.getItem("jwt");
  const {auth}=useSelector(store=>store)


  useEffect(()=>{
    if(jwt)dispatch(getUser(jwt));
  },[jwt,auth.jwt]);



  const handleSubmit = (e) => {
    e.preventDefault();
    const data = new FormData(e.currentTarget);
    const userData = {
      firstName: data.get("firstName"),
      lastName: data.get("lastName"),
      email: data.get("email"),
      password: data.get("password"),
    };
     dispatch(register(userData))
    console.log(userData);
  };

  return (
    <div style={{ padding: "1.5rem" }}>
      <form onSubmit={handleSubmit}>
        <Grid container spacing={3}>
          <Grid item xs={12} sm={6}>
            <TextField
              required
              id="firstName"
              name="firstName"
              label="First Name"
              autoComplete="given-name"
              fullWidth
            />
          </Grid>
          <Grid item xs={12} sm={6}>
            <TextField
              required
              id="lastName"
              name="lastName"
              label="Last Name"
              autoComplete="family-name"
              fullWidth
            />
          </Grid>
          <Grid item xs={12}>
            <TextField
              required
              id="email"
              name="email"
              label="Email"
              type="email"
              autoComplete="email"
              fullWidth
            />
          </Grid>
          <Grid item xs={12}>
            <TextField
              required
              id="password"
              name="password"
              label="Password"
              type="password"
              autoComplete="current-password"
              fullWidth
            />
          </Grid>
          <Grid item xs={12}>
            <Button
              type="submit"
              variant="contained"
              size="large"
              fullWidth
              sx={{
                backgroundColor: "#9155fd",
                ":hover": { backgroundColor: "#7a4dff" },
                padding: "1rem",
              }}
            >
              Register
            </Button>
          </Grid>
        </Grid>
      </form>
      <div className="flex justify-center flex-col items-center mt-5">
        <div className="py-3 flex items-center">
          <p>
            If you already have an account,{" "}
            <Button
              onClick={() => navigate("/login")}
              size="small"
              sx={{ marginLeft: "0.5rem", textTransform: "none",color: "#9155fd" }}
            >
              Login
            </Button>
          </p>
        </div>
      </div>
    </div>
  );
};

export default RegisterForm;
