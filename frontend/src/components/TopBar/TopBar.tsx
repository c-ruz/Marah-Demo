import { AppBar, Box, Tooltip, Typography } from '@mui/material'
import useGameStore from '../../store/GameStateStore.ts'
import ThemeModeSwitch from '../ThemeModeSwitch/ThemeModeSwitch.tsx'

interface TopBarProps {
  message: string
}

function TopBar({ message }: TopBarProps) {
  const { gameStateLoading } = useGameStore()

  return (
    <AppBar
      position="static"
      elevation={4}
      sx={{
        p: 2,
        bgcolor: 'primary.main', color: 'primary.contrastText',
        flexDirection: 'row',
        alignItems: 'center',
      }}
    >
      <Box mr="auto" />
      <Tooltip title={message} arrow>
        <Typography
          variant="h5"
          component="div"
          textAlign="center"
          sx={{ overflow: 'hidden',
            whiteSpace: 'nowrap',
            textOverflow: 'ellipsis' }}
        >
          {gameStateLoading ? 'Loading...' : message}
        </Typography>
      </Tooltip>
      <Box ml="auto">
        <ThemeModeSwitch />
      </Box>
    </AppBar>
  )
}

export default TopBar
