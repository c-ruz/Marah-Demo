import { ThemeProvider } from '@mui/material/styles'
import { theme } from './theme.ts'
import { Alert, Box, Fade, List, ListItem, ListItemText, Paper, Snackbar, Stack } from '@mui/material'
import TopBar from './components/TopBar/TopBar.tsx'
import useGameStore from './store/GameStateStore.ts'
import ActionMenu from './components/ActionMenu/ActionMenu.tsx'
import type { Score } from './types/types.ts'
import { useEffect } from 'react'
import Map from './components/Map/Map.tsx'

type ActionMessage = { message: string, severity: 'success' | 'error' }
interface ActionFeedbackProps {
  open: boolean
  message: ActionMessage
}

function ActionFeedback({ open, message }: ActionFeedbackProps) {
  const { toggleActionMessage } = useGameStore()

  return (
    <Snackbar
      open={open}
      onClose={toggleActionMessage}
      autoHideDuration={5000}
      anchorOrigin={{ vertical: 'bottom', horizontal: 'center' }}
      slots={{ transition: Fade }}
    >
      <Alert
        severity={message.severity}
        variant="filled"
      >
        {message.message}
      </Alert>
    </Snackbar>
  )
}

interface BottomMenuProps {
  scores: Score[]
}

function BottomMenu({ scores }: BottomMenuProps) {
  return (
    <Paper elevation={4} sx={{ p: 1, bgcolor: 'background.paper', color: 'text.primary', borderRadius: 0 }}>
      <Stack
        spacing={1}

        direction="row"
        height="20vh"

        alignItems="stretch"
        justifyContent="space-between"
      >
        {/* Action Buttons */}
        <ActionMenu />

        {/* Score Display */}
        <Box width="50%" overflow="auto">
          <List>
            {scores.map((s, i) => (
              <ListItem key={`${s.name}_${i}`}>
                <ListItemText primary={`${s.name} ${s.value}`} />
              </ListItem>
            ))}
          </List>
        </Box>
      </Stack>
    </Paper>
  )
}

function App() {
  const { gameState: { scores, topBarMessage }, actionMessage, showActionMessage, fetchGameState } = useGameStore()

  useEffect(() => {
    fetchGameState()
  }, [])

  return (
    <ThemeProvider theme={theme}>
      <Stack
        direction="column"
        sx={{
          height: '100vh',
          color: 'white',
          backgroundColor: 'background.default',
        }}
      >
        <TopBar message={topBarMessage || 'Choose an action...'} />
        <ActionFeedback message={actionMessage} open={showActionMessage} />
        <Box height="80vh" sx={{ p: 2, overflowY: 'auto' }}>
          <Map />
        </Box>
        <BottomMenu scores={scores} />
      </Stack>
    </ThemeProvider>
  )
}

export default App
