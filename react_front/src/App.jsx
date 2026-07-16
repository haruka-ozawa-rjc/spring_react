import { Routes, Route } from "react-router-dom"
import Menu from "./pages/Menu.jsx"
import List from "./pages/List.jsx"
import Insert from "./pages/Insert.jsx"
import InsertConf from "./pages/InsertConf.jsx"
import InsertComp from "./pages/InsertComp.jsx"
import Detail from "./pages/Detail.jsx"
import Update from "./pages/Update.jsx"
import UpdateConf from "./pages/UpdateConf.jsx"
import UpdateComp from "./pages/UpdateComp.jsx"
import Delete from "./pages/Delete.jsx"
import DeleteComp from "./pages/DeleteComp.jsx"

function App() {

  return(
    <>
      <Routes>
        <Route path="/" element={<Menu />} />
        <Route path="/list" element={<List />} />
        <Route path="/insert" element={<Insert />} />
        <Route path="/insertConf" element={<InsertConf />} />
        <Route path="/insertComp" element={<InsertComp />} />
        <Route path="/detail/:id" element={<Detail />} />
        <Route path="/update" element={<Update />} />
        <Route path="/update/:id" element={<Update />} />
        <Route path="/updateConf" element={<UpdateConf />} />
        <Route path="/updateComp" element={<UpdateComp />} />
        <Route path="/delete/:id" element={<Delete />} />
        <Route path="/deleteComp" element={<DeleteComp />} />
      </Routes>
    </>
  )
}

export default App;