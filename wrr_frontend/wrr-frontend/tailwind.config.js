/** @type {import('tailwindcss').Config} */
export default {
  content: [
    "./index.html",
    "./src/**/*.{vue,js,ts,jsx,tsx}",
  ],
  theme: {
    extend: {
      colors: {
        logo: {
          500: "#FF7F3E"
        },
        back: "#003285"
      },
    },
  },
  plugins: [],
}
