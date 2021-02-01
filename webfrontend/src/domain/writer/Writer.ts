
const WriterType = Symbol()

export class Writer {
    [WriterType]: any

    id: string
    name: string

    constructor(id: string, name: string) {
        this.id = id
        this.name = name
    }
}
