import { Button, Grid, TextField } from "@mui/material";
import React, { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import { getUser, login } from "../../State/Auth/Action";

export const LoginForm = () => {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const jwt = localStorage.getItem("jwt");
  const {auth}=useSelector(store=>store)
  const handleSubmit = (e) => {
    e.preventDefault();
    const data = new FormData(e.currentTarget);
    const userData = {
      email: data.get("email"),
      password: data.get("password"),
    };
    dispatch(login(userData));
    console.log(userData);
  };
    useEffect(()=>{
      if(jwt)dispatch(getUser(jwt));
    },[jwt,auth.jwt]);

  return (
    <div style={{ padding: "1.5rem" }}>
      <form onSubmit={handleSubmit}>
        <Grid container spacing={3}>
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
                bgcolor: "#9155FD",
                color: "white",
                "&:hover": {
                  bgcolor: "#7A4DDB",
                },
              }}
            >
              Login
            </Button>
          </Grid>
        </Grid>
      </form>
       <div className="flex justify-center flex-col items-center mt-5">
              <div className="py-3 flex items-center">
                <p>
                Don't have an account?{" "}
                  <Button
                    onClick={() => navigate("/signin")}
                    size="small"
                    sx={{ marginLeft: "0.5rem", textTransform: "none",color: "#9155fd" }}
                  >
                    Signin
                  </Button>
                </p>
              </div>
            </div>
    </div>
  );
};
