import { Container, TextField } from "@mui/material";
import { Box } from "@mui/system";
import Tools from "../components/tool/Tools";

const ToolsCreatePage = () => {
  return (
    <Container maxWidth="fixed">
      <Box
        component="form"
        sx={{
          "& .MuiTextField-root": { m: 1, width: "25ch" },
        }}
        noValidate
        autoComplete="off"
      >
        <div>
          <TextField
            required
            id="outlined-required"
            label="Required"
            defaultValue="Hello World"
          />
        </div>
      </Box>
    </Container>
  );
};

export default ToolsCreatePage;
