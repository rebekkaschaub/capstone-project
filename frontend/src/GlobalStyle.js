import { createGlobalStyle } from "styled-components";

export default createGlobalStyle`
    *{
      box-sizing: border-box;
    }
    
    html, body{
      margin: 0;
      padding: 8px;
      font-family: 'Montserrat', sans-serif;
      color: #1c3648;
    }

    input, button {
      font-size: 1em;
      font-family: inherit;
      color: inherit;
    }
`;
