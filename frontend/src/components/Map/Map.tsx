import { Box } from '@mui/material'
import useGameStore from '../../store/GameStateStore.ts'
import Panel from '../MapPanel/MapPanel.tsx'

function Map() {
  const { gameState: { gridSize, cells } } = useGameStore()
  const { x: maxX, y: maxY } = gridSize

  return (
    <Box
      sx={{
        display: 'grid',
        gridTemplateColumns: `repeat(${maxX}, 1fr)`,
        gridTemplateRows: `repeat(${maxY}, 1fr)`,
        minWidth: '100%',
        minHeight: '100%',
        gap: 1,
      }}
    >
      {cells.map((panel, index) => (
        <Panel key={index} panel={panel} />
      ))}
    </Box>
  )
}

export default Map
