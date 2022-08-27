import TopBar from "./components/TopBar";
import { Route, Routes } from "react-router-dom";
import ToolsPage from "./page/ToolsPage";
import ToolsCreatePage from "./page/ToolsCreatePage";

function App() {
  return (
    <div>
      <TopBar />
      <Routes>
        <Route path="/" element={<ToolsPage />} />
        <Route path="/tools/create" element={<ToolsCreatePage />} />
      </Routes>
    </div>
  );
}

export default App;
