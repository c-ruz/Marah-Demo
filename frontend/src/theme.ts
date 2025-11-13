import { createTheme } from '@mui/material/styles'

// 1. Define the color palettes for light and dark modes
const lightPalette = {
  primary: {
    main: '#1976d2', // A classic, friendly blue
    light: '#42a5f5',
    dark: '#1565c0',
    contrastText: '#fff',
  },
  secondary: {
    main: '#009688', // A vibrant teal for accents
    light: '#4db6ac',
    dark: '#00796b',
    contrastText: '#fff',
  },
  background: {
    default: '#eeeeefff', // A very light grey for the background
    paper: '#ffffff', // White for cards and surfaces
  },
  text: {
    primary: '#2c3e50', // A dark, soft black for text
    secondary: '#7f8c8d',
  },
}

const darkPalette = {
  primary: {
    main: '#42a5f5', // Lighter blue for dark mode
    light: '#64b5f6',
    dark: '#2196f3',
    contrastText: '#000',
  },
  secondary: {
    main: '#4db6ac', // Lighter teal for dark mode
    light: '#80cbc4',
    dark: '#26a69a',
    contrastText: '#000',
  },
  background: {
    default: '#1d1c1cff', // A standard dark background
    paper: '#232323ff', // A slightly lighter dark for surfaces
  },
  text: {
    primary: '#ffffff',
    secondary: '#bdc3c7',
  },
}

export const theme = createTheme({
  colorSchemes: {
    light: {
      palette: lightPalette,
    },
    dark: {
      palette: darkPalette,
    },
  },
  typography: {
    fontFamily:
      '"Silkscreen", "VT323", "Pixelify Sans", "Inter", "Roboto", "Helvetica", "Arial", sans-serif',
    h5: {
      fontWeight: 700,
      letterSpacing: '0.5px',
    },
  },
})
