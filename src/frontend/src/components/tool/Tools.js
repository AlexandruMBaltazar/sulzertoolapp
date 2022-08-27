import axios from "axios";
import { useEffect, useState } from "react";
import { DataGrid, GridToolbar } from "@mui/x-data-grid";
import { Button, Stack } from "@mui/material";
import DeleteIcon from "@mui/icons-material/Delete";
import { Link } from "react-router-dom";

const Tools = () => {
  const [page, setPage] = useState(0);
  const [pageSize, setPageSize] = useState();
  const [tools, setTools] = useState([]);
  const [isLoading, setIsLoading] = useState(true);
  const [rowCountState, setRowCountState] = useState(0);

  const columns = [
    { field: "toolNumber", headerName: "Tool No", width: 200 },
    { field: "toolClass", headerName: "Tool Class", width: 200 },
    { field: "toolAtms", headerName: "Tool ATMS", width: 200 },
    { field: "toolAtmsNumber", headerName: "Tool ATMS No", width: 200 },
    { field: "diameter", headerName: "Diameter", width: 200 },
    { field: "tipRadius", headerName: "Tip Radius", width: 200 },
    { field: "length", headerName: "Length", width: 200 },
    { field: "comment", headerName: "Comment", width: 200 },
    { field: "createdAt", headerName: "Created At", width: 200 },
    { field: "updatedAt", headerName: "Updated At", width: 200 },
    {
      field: "actions",
      headerName: "Actions",
      description: "Edit or Delete a Tool from here",
      sortable: false,
      width: 300,
      renderCell: (params) => (
        <Stack direction="row" spacing={13}>
          <Button
            component={Link}
            to={`/tools/${params.row.id}/edit`}
            variant="contained"
          >
            Edit
          </Button>
          <Button variant="outlined" color="error" startIcon={<DeleteIcon />}>
            Delete
          </Button>
        </Stack>
      ),
    },
  ];
  useEffect(() => {
    const fetchTools = () => {
      setIsLoading(true);
      axios
        .get(`/api/v1/tools?page=${page}&size=${pageSize}`)
        .then((response) => {
          setTools(response.data.content);
          setRowCountState(response.data.totalElements);
          setIsLoading(false);
        });
    };

    fetchTools();
  }, [page, pageSize]);

  return (
    <div style={{ margin: "5rem", height: 600 }}>
      <Button
        component={Link}
        to={`/tools/create`}
        style={{ marginBottom: "1rem" }}
        variant="contained"
        size="large"
      >
        Create New Tool
      </Button>
      <div style={{ display: "flex", height: "100%" }}>
        <div style={{ flexGrow: 1 }}>
          <DataGrid
            paginationMode="server"
            page={page}
            onPageChange={(newPage) => setPage(newPage)}
            rowCount={rowCountState}
            rows={tools}
            columns={columns}
            components={{ Toolbar: GridToolbar }}
            pageSize={pageSize}
            onPageSizeChange={(newPageSize) => setPageSize(newPageSize)}
            rowsPerPageOptions={[25, 50, 100]}
            loading={isLoading}
            pagination
          />
        </div>
      </div>
    </div>
  );
};

export default Tools;
