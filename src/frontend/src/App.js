import './App.scss';

import Home from './pages/home/home'
import Sidebar from "./components/sidebar/sidebar";

function App() {
  return (
    <div className="App">
        <Sidebar/>
        <Home/>
    </div>
  );
}

export default App;
