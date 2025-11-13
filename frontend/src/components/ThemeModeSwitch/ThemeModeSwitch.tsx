import { DarkMode, LightMode } from '@mui/icons-material'
import { IconButton } from '@mui/material'
import { useColorScheme } from '@mui/material/styles'

function ThemeModeSwitch() {
  const { mode, setMode } = useColorScheme()

  return (
    <IconButton
      onClick={() => {
        setMode(mode === 'light' ? 'dark' : 'light')
      }}
      color="inherit"
    >
      {mode === 'light' ? <LightMode /> : <DarkMode />}
    </IconButton>
  )
}

export default ThemeModeSwitch
