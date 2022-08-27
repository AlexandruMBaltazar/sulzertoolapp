import { Api } from "@mui/icons-material";
import {
  Box,
  Button,
  Card,
  CardActions,
  CardContent,
  FormControl,
  InputLabel,
  MenuItem,
  Select,
  TextField,
  Typography,
} from "@mui/material";
import axios from "axios";
import { useState } from "react";
import { Navigate, useNavigate } from "react-router-dom";

const ToolForm = () => {
  const [toolForm, setToolForm] = useState({
    toolNumber: "",
    toolClass: "BOR",
    toolAtms: "PREBOR",
    toolAtmsNumber: "",
    diameter: null,
    tipRadius: null,
    length: null,
    comment: "",
  });

  const [errors, setErrors] = useState([]);

  const [pendingApiCall, setPendingApiCall] = useState(false);
  let navigate = useNavigate();

  const onChange = (event) => {
    const { value, name } = event.target;
    setToolForm((previousForm) => {
      return {
        ...previousForm,
        [name]: value,
      };
    });

    setErrors((previousErrors) => {
      return {
        ...previousErrors,
        [name]: undefined,
      };
    });
  };

  const onSubmit = () => {
    setPendingApiCall(true);
    axios
      .post("/api/v1/tools", toolForm)
      .then((response) => {
        setPendingApiCall(false);
        navigate("/");
      })
      .catch((error) => {
        if (error.response.data && error.response.data.validationErrors) {
          setErrors(error.response.data.validationErrors);
        }
        setPendingApiCall(false);
      });
  };

  return (
    <Card sx={{ width: "100%", mt: 5 }}>
      <CardContent>
        <Typography gutterBottom variant="h5" component="div">
          Tool Details
        </Typography>
        <Box sx={{ mt: 3, mb: 3 }}>
          <TextField
            required
            name="toolNumber"
            onChange={onChange}
            value={toolForm.toolNumber}
            label={errors.toolNumber ? "Error" : "Tool No"}
            error={errors.toolNumber ? true : false}
            helperText={errors.toolNumber ?? ""}
          />
          <FormControl sx={{ mt: 2 }} fullWidth required>
            <InputLabel>Tool Class</InputLabel>
            <Select
              required
              name="toolClass"
              value={toolForm.toolClass}
              label="Tool Class"
              onChange={onChange}
            >
              <MenuItem value={"DRILL"}>DRILL</MenuItem>
              <MenuItem value={"THREAD"}>THREAD</MenuItem>
              <MenuItem value={"TURN"}>TURN</MenuItem>
              <MenuItem value={"MILL"}>MILL</MenuItem>
              <MenuItem value={"BOR"}>BOR</MenuItem>
            </Select>
          </FormControl>
          <Box
            sx={{
              mt: 2,
              display: "flex",
              justifyContent: "space-between",
              gap: 1,
            }}
          >
            <FormControl sx={{ width: "50%" }} required>
              <InputLabel>Tool ATMS</InputLabel>
              <Select
                required
                name="toolAtms"
                value={toolForm.toolAtms}
                label="Tool ATMS"
                onChange={onChange}
              >
                <MenuItem value={"PRETRN"}>PRETRN</MenuItem>
                <MenuItem value={"PREMIL"}>PREMIL</MenuItem>
                <MenuItem value={"PREBOR"}>PREBOR</MenuItem>
                <MenuItem value={"PRETHD"}>PRETHD</MenuItem>
              </Select>
            </FormControl>
            <TextField
              sx={{ width: "50%" }}
              name="toolAtmsNumber"
              required
              onChange={onChange}
              value={toolForm.toolAtmsNumber}
              label={errors.toolAtmsNumber ? "Error" : "Tool ATMS No"}
              error={errors.toolAtmsNumber ? true : false}
              helperText={errors.toolAtmsNumber ?? ""}
            />
          </Box>
        </Box>
        <Typography gutterBottom variant="h5" component="div">
          Geometry
        </Typography>
        <Box sx={{ mt: 3, mb: 3 }}>
          <Box
            sx={{
              mt: 2,
              display: "flex",
              justifyContent: "space-between",
              gap: 1,
            }}
          >
            <TextField
              type="number"
              name="diameter"
              sx={{ width: "50%" }}
              label="Diameter"
              onChange={onChange}
              value={toolForm.diameter}
              InputProps={{
                inputProps: {
                  min: 0,
                },
              }}
            />
            <TextField
              type="number"
              name="tipRadius"
              sx={{ width: "50%" }}
              label="Tip Radius"
              onChange={onChange}
              value={toolForm.tipRadius}
              InputProps={{
                inputProps: {
                  min: 0,
                },
              }}
            />
            <TextField
              type="number"
              name="length"
              sx={{ width: "50%" }}
              label="Length"
              onChange={onChange}
              value={toolForm.length}
              InputProps={{
                inputProps: {
                  min: 0,
                },
              }}
            />
          </Box>
        </Box>
        <Typography gutterBottom variant="h5" component="div">
          Comment
        </Typography>
        <Box sx={{ mt: 3, mb: 3 }}>
          <TextField
            name="comment"
            fullWidth
            label="Comment"
            multiline
            rows={4}
            onChange={onChange}
            value={toolForm.comment}
          />
        </Box>
      </CardContent>
      <CardActions>
        <Button onClick={() => navigate("/")} size="small">
          Cancel
        </Button>
        <Button size="small" onClick={onSubmit}>
          Save
        </Button>
      </CardActions>
    </Card>
  );
};

export default ToolForm;
