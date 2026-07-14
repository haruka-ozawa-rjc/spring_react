import { Routes, Route } from "react-router-dom"
import Menu from "./pages/Menu.jsx"
import List from "./pages/List.jsx"
import Insert from "./pages/Insert.jsx"
import InsertConf from "./pages/InsertConf.jsx"
import InsertComp from "./pages/InsertComp.jsx"

function App() {

  return(
    <>
      <Routes>
        <Route path="/" element={<Menu />} />
        <Route path="/list" element={<List />} />
        <Route path="/insert" element={<Insert />} />
        <Route path="/insertConf" element={<InsertConf />} />
        <Route path="/insertComp" element={<InsertComp />} />
      </Routes>
    </>
  )
}

export default App;