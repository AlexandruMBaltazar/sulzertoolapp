import { Container } from "@mui/material";
import ToolForm from "../components/tool/ToolForm";
import Tools from "../components/tool/Tools";

const ToolsCreatePage = () => {
  return (
    <Container maxWidth="md" style={{ position: "relative" }}>
      <ToolForm />
    </Container>
  );
};

export default ToolsCreatePage;
