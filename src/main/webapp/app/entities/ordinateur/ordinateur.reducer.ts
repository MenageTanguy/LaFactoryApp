import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IOrdinateur, defaultValue } from 'app/shared/model/ordinateur.model';

export const ACTION_TYPES = {
  FETCH_ORDINATEUR_LIST: 'ordinateur/FETCH_ORDINATEUR_LIST',
  FETCH_ORDINATEUR: 'ordinateur/FETCH_ORDINATEUR',
  CREATE_ORDINATEUR: 'ordinateur/CREATE_ORDINATEUR',
  UPDATE_ORDINATEUR: 'ordinateur/UPDATE_ORDINATEUR',
  DELETE_ORDINATEUR: 'ordinateur/DELETE_ORDINATEUR',
  RESET: 'ordinateur/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IOrdinateur>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type OrdinateurState = Readonly<typeof initialState>;

// Reducer

export default (state: OrdinateurState = initialState, action): OrdinateurState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_ORDINATEUR_LIST):
    case REQUEST(ACTION_TYPES.FETCH_ORDINATEUR):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_ORDINATEUR):
    case REQUEST(ACTION_TYPES.UPDATE_ORDINATEUR):
    case REQUEST(ACTION_TYPES.DELETE_ORDINATEUR):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_ORDINATEUR_LIST):
    case FAILURE(ACTION_TYPES.FETCH_ORDINATEUR):
    case FAILURE(ACTION_TYPES.CREATE_ORDINATEUR):
    case FAILURE(ACTION_TYPES.UPDATE_ORDINATEUR):
    case FAILURE(ACTION_TYPES.DELETE_ORDINATEUR):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_ORDINATEUR_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_ORDINATEUR):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_ORDINATEUR):
    case SUCCESS(ACTION_TYPES.UPDATE_ORDINATEUR):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_ORDINATEUR):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {}
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState
      };
    default:
      return state;
  }
};

const apiUrl = 'api/ordinateurs';

// Actions

export const getEntities: ICrudGetAllAction<IOrdinateur> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_ORDINATEUR_LIST,
  payload: axios.get<IOrdinateur>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IOrdinateur> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_ORDINATEUR,
    payload: axios.get<IOrdinateur>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IOrdinateur> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_ORDINATEUR,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IOrdinateur> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_ORDINATEUR,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IOrdinateur> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_ORDINATEUR,
    payload: axios.delete(requestUrl)
  });
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
