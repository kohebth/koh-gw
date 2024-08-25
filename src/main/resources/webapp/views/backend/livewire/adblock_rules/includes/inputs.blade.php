<div class="form-group row">
    {{ html()->label('Value <span class="text-danger">(*)</span>')->class('col-sm-2 form-control-label')->for('value') }}
    <div class="col-sm-10">
        <input type="text" wire:model.defer="adblockRules.rule" class="form-control">
        @error('adblockRules.rule')<x-error-message>{{$message}}</x-error-message> @enderror
    </div>
</div>
<div class="form-group row">
    {{ html()->label('Type <span class="text-danger">(*)</span>')->class('col-sm-2 form-control-label')->for('type') }}
    <div class="col-sm-10">
        <select wire:model.defer="adblockRules.type" class="form-control">
            <option value=""></option>
            @foreach($types as $type)
                <option value="{{$type}}">{{__($type)}}</option>
            @endforeach
        </select>
        @error('adblockRules.type')<x-error-message>{{$message}}</x-error-message> @enderror
    </div>
</div>
<div class="form-group row">
    {{ html()->label('Status <span class="text-danger">(*)</span>')->class('col-sm-2 form-control-label')->for('status') }}
    <div class="col-sm-10">
        <select wire:model.defer="adblockRules.status" class="form-control">
            <option value=""></option>
            @foreach($status as $value)
                <option value="{{$value}}">{{__($value)}}</option>
            @endforeach
        </select>
        @error('adblockRules.status')<x-error-message>{{$message}}</x-error-message> @enderror
    </div>
</div>
<div class="form-group row">
    {{ html()->label('Note')->class('col-sm-2 form-control-label')->for('note') }}
    <div class="col-sm-10">
        <input type="text" wire:model.defer="adblockRules.note" class="form-control">
        @error('adblockRules.note')<x-error-message>{{$message}}</x-error-message> @enderror
    </div>
</div>
