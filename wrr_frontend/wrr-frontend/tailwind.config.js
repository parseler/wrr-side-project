/** @type {import('tailwindcss').Config} */
export default {
  content: [
    "./index.html",
    "./src/**/*.{vue,js,ts,jsx,tsx}",
  ],
  theme: {
    extend: {
      colors: {
        logo: "#fca311",
        back: "#015807",
        nav: "#015807",
        word: "#fefae0",
        btn: {
          700: "#fca311",
          500: "#EF9B0F"
        }
      }
    },
  },
  plugins: [],
}
