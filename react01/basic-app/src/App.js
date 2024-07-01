import logo from './logo.svg';
import './App.css';
import CustomButton from './component/CustomButton';

// 데이터 생성시 보통 const
const ironMan = {
  name: 'Toney Stark1',
  heroName: 'ironMan',
  imgUrl: 'https://img.danawa.com/prod_img/500000/207/533/img/18533207_1.jpg?_v=20221226163359',
  imgSize: 100
}

const weapons = [
  { title: 'Replusor Beam', idx: 1 },
  { title: 'Unibeam Blaster', idx: 2 },
  { title: 'Smart Missile', idx: 3 }
];

const listWeapons = weapons.map(weapon =>
  <li key={weapon.idx}>
    {weapon.title}
  </li>
);

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <h1>{ironMan.heroName}</h1>
        <img className='profile'
          src={ironMan.imgUrl}
          alt={ironMan.name + '변신사진'}
          style={{
            width: ironMan.imgSize,
            height: ironMan.imgSize
          }}/>
        <ul>{listWeapons}</ul>
        <CustomButton />
      </header>
    </div>
  );
}

export default App;
