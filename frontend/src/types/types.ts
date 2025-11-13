export interface Action {
  id: string
  name: string
}

export interface EntityAttribute {
  name: string
  value: string
}

export interface PanelEntity {
  name: string
  attributes: EntityAttribute[]
  actions: Action[]
  img?: string
}

export interface Panel {
  label?: string
  x: number
  y: number
  entities: PanelEntity[]
  actions: Action[]
  img?: string
}

export interface Score {
  name: string
  value: string
}
