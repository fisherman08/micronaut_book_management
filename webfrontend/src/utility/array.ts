export const ArrayUtils = {
    // オブジェクトの配列を、そのオブジェクト内の特定のキーでグルーピングしたmapとして返す
    groupBy: <K extends PropertyKey, V>(
        array: readonly V[],
        getKey: (cur: V, idx: number, src: readonly V[]) => K
    ) => {
        type ReturnType = { [key in K]: V[] };
        return array.reduce((obj, cur, idx, src) => {
            const key = getKey(cur, idx, src);
            (obj[key] || (obj[key] = []))!.push(cur);
            return obj;
        }, {} as ReturnType);
    }
};
